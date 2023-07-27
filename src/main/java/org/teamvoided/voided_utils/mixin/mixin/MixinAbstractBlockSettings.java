package org.teamvoided.voided_utils.mixin.mixin;


//@Mixin(AbstractBlock.Settings.class)
//public class MixinAbstractBlockSettings implements BlockSettingsLock {
//	@Unique
//	private boolean terraform$locked = false;
//
//	@Inject(method = "sounds", at = @At("HEAD"), cancellable = true)
//	private void terraform$preventSoundsOverride(CallbackInfoReturnable<AbstractBlock.Settings> ci) {
//		if (this.terraform$locked) {
//			ci.setReturnValue((AbstractBlock.Settings) (Object) this);
//			this.terraform$locked = false;
//		}
//	}
//
//	@Override
//	public void lock() {
//		this.terraform$locked = true;
//	}
//}
